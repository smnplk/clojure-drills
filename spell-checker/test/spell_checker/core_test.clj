(ns spell-checker.core-test
  (:require [clojure.test :refer :all]
            [spell-checker.levenshtein :as levenshtein]
            [spell-checker.core :refer :all]))


(deftest levenshtein

  (testing "levensthein/edit-distance returns an integer"
    (is (instance? Long (levenshtein/edit-distance "Aba" "Abakus"))))

  (testing "levensthein/edit-distance should return minimum edit distance"
    (do
      (is (= 1 (levenshtein/edit-distance "Meow" "Meowa")))
      (is (= 3 (levenshtein/edit-distance "Something" "Nothing")))
      (is (= 9 (levenshtein/edit-distance "Completely" "Weird"))))))
