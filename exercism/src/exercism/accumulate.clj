(ns exercism.accumulate)

; Implement the accumulate operation, which, given a collection and an operation to perform on each element of the collection,
; returns a new collection containing the result of applying that operation to each element of the input collection.
; Given the collection of numbers:
; 1, 2, 3, 4, 5I
; And the operation:
; square a number (x => x * x)
; Your code should be able to produce the collection of squares:
; 1, 4, 9, 16, 25
; Check out the test suite to see the expected function signature.

; SPECIAL RESTRICTIONS: You can not use map


; solution with a loop, not very functional
(def large-vec (vec (range 10000)))

(defn acc-1 [f coll]
  (loop [c coll acc []]
    (if (empty? c)
        acc
        (recur (rest c) (conj acc (f (first c)))))))

(acc-1 #(* % %) [1 2 3 4 5])
(acc-1 #(* % %) '(1 2 3 4 5))
(time (acc-1 #(* % %) large-vec)) ; about 12 ms

; another way, to use doseq, again, not very functional,
; but performance is better because of the use of transient ds
(defn acc-2 [f coll]
  (let [acc (transient [])]
    (doseq [e coll]
      (conj! acc (f e)))
    (persistent! acc)))

(acc-2 #(* % %) [1 2 3 4 5])
(acc-2 #(* % %) '(1 2 3 4 5))
(time (acc-2 #(* % %) large-vec)) ; about 4ms


; using recursion directly,using a closure, not very performant, blows stack soon
; that is why using recur
(defn acc-3 [f coll]
  (letfn [(accumulate [coll acc]
            (if (empty? coll)
                acc
                (accumulate (rest coll) (conj acc (f (first coll))))))]
    (accumulate coll [])))

(acc-3 #(* % %) [1 2 3 4 5])
(acc-3 #(* % %) '(1 2 3 4 5))
(time (acc-3 #(* % %) large-vec)) ; StackOwerflow !! :)

; using the same solution as above, but instead of recursively calling inner function
; we can use recur instead, looks recursive but under the covers, it is converted to iteration
; using recur, recur is the only non stack consuming looping construct
(defn acc-4 [f coll]
  (letfn [(accumulate [coll acc]
            (if (empty? coll)
              acc
              (recur (rest coll) (conj acc (f (first coll))))))]
    (accumulate coll [])))

(acc-4 #(* % %) [1 2 3 4 5])
(acc-4 #(* % %) '(1 2 3 4 5))
(time (acc-4 #(* % %) large-vec)) ; Look ma, no stack owerflow ! :)


; let's get real, we can also use reduce and be more functional :P They didn't say that we
; cant use reduce in the instructions

(defn acc-5 [f coll]
  (reduce (fn [acc input]
            (conj acc (f input))) [] coll))

(acc-5 #(* % %) [1 2 3 4 5])
(acc-5 #(* % %) '(1 2 3 4 5))
(time (acc-5 #(* % %) large-vec)) ; 7ms, not bad ;)

; don't forget about list comprehensions :)

(defn acc-6 [f coll]
  (for [e coll :let [prod (f e)]]
       prod))

(acc-6 #(* % %) [1 2 3 4 5])
(acc-6 #(* % %) '(1 2 3 4 5))
(time (acc-6 #(* % %) large-vec)) ; 0.6ms, whooa! And this is my favourite way if i am not allowed to use map

