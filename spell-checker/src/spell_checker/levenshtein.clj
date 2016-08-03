; Implementation of levenshtein distance / minimum edit distance
; https://www.youtube.com/watch?v=We3YDTzNXEk"})
; This is a functional approach, for really large strings (n > 1000)
; org.apache.commons.lang3 StringUtils/getLevenshteinDistance is more efficient

(ns spell-checker.levenshtein)

(defn- calc-next-row [prev-row current-char other-string]
  (reduce
    (fn [row [diagonal above other-char]]
      (let [update-val
            (if (= current-char other-char)
              diagonal
              (inc (min diagonal above (peek row))))]
        (conj row update-val)))

    [(inc (first prev-row))]

    (map vector prev-row (rest prev-row) other-string)))


(defn edit-distance
  "Returns an integer, representing the minimum amount of changes to convert from-string into to-string"
  [from-string to-string]
  (peek
    (reduce
      (fn [prev-row current-char]
        (calc-next-row prev-row current-char from-string))

      (range 0 (inc (.length from-string)))

      to-string)))
