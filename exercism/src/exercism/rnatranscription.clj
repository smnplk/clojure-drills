(ns exercism.rnatranscription)

; Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).
; Both DNA and RNA strands are a sequence of nucleotides.
; The four nucleotides found in DNA are adenine (A), cytosine (C), guanine (G) and thymine (T).
; The four nucleotides found in RNA are adenine (A), cytosine (C), guanine (G) and uracil (U).
; Given a DNA strand, its transcribed RNA strand is formed by replacing each nucleotide with its complement:

; G -> C
; C -> G
; T -> A
; A -> U

(def complements-map { \G \C, \C \G, \T \A, \A \U})

(defn to-rna [dna-str]
  (reduce (fn [rna-str nucleotide]
            (if-let [transcribed (get complements-map nucleotide)]
              (str rna-str transcribed)
              (throw (AssertionError. "Invalid DNA sequence"))))
          ""
          dna-str))

(to-rna "GCTAAACTTTTTG")
