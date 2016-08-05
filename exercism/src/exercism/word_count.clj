(ns exercism.word-count)

; reduce is king :)

(defn word-count [sentence]
  (reduce #(assoc %1  %2 (inc (get %1 %2 0))) {} (re-seq #"\w+" (.toLowerCase sentence))))
