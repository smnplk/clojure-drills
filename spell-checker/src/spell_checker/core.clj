(ns spell-checker.core
  (:require [clojure.string :as str])
  (:require [spell-checker.levenshtein :as levensthein])
  (use clojure.tools.trace)
  (:gen-class))

(def dictionary
  (->> (slurp "resources/brit-a-z.txt")
       (str/split-lines)
       (map str/trim)
       (set)))

(defn- is-word-correct?
  "Checks if the word is in the dictionary"
  [word]
  (contains? dictionary word))

(defn- distance [word1 word2]
  (if (= word1 word2)
    0
    (levensthein/edit-distance word2 word1)))

(defn find-similar-word
  "returns a word from dictionary that is most similar to input-word"
  [input-word]
  (apply min-key (partial distance input-word) dictionary))
