(ns exercism.rnatranscription-test
  (:require [clojure.test :refer [deftest is]]
            [exercism.rnatranscription :as rnatranscription]))

(deftest transcribes-cytosine-to-guanine
  (is (= "G" (rnatranscription/to-rna "C"))))

(deftest transcribes-guanine-to-cytosine
  (is (= "C" (rnatranscription/to-rna "G"))))

(deftest transcribes-adenine-to-uracil
  (is (= "U" (rnatranscription/to-rna "A"))))

(deftest it-transcribes-thymine-to-adenine
  (is (= "A" (rnatranscription/to-rna "T"))))

(deftest it-transcribes-all-nucleotides
  (is (= "UGCACCAGAAUU" (rnatranscription/to-rna "ACGTGGTCTTAA"))))

(deftest it-validates-dna-strands
  (is (thrown? AssertionError (rnatranscription/to-rna "XCGFGGTDTTAA"))))
