(ns exercism.accumulate-test
  (:require [clojure.test :refer :all]
            [exercism.accumulate :refer :all]))

(deftest accumulate-test
  (testing "doubling every number"
    (is (= [2 4 6] (acc-6 #(* % 2) [1 2 3])))))
