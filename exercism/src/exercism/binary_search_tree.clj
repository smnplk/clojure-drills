(ns exercism.binary-search-tree)

; we can represent a tree as nested vectors
; for example
;      3
;     / \
;    1   5
;         \
;          6
; can be represented as [3 [1 nil nil] [5 nil [6 nil nil]]]
; so first element is value, second is left node, last is right node


(defn singleton [val]
  "construcs a leaf node"
  [val nil nil])

(defn value
  "returns the node value"
  [node]
  (first node))

(defn left
  "left node/subtree"
  [node]
  (second node))

; peek returns the last element of a vector, more performant than last
(defn right
  "right node/subtree"
  [node]
  (peek node))

(defn insert [val node]
  (let [node-val (value node)]
    (cond
      (nil? node) (singleton val)
      (<= val node-val) [node-val (insert val (left node)) (right node)]
      (> val node-val)  [node-val (left node) (insert val (right node))])))

(defn from-list [s]
  (reduce #(insert %2 %1) nil s))

(defn to-list [node]
  (when node
    (concat (to-list (left node))
            [(value node)]
            (to-list (right node)))))
