(ns aoc-clojure.day1
  (:gen-class))
(defn calc
  "Calculates a value, then adds a value to it"
  [calc]
  (- (Math/floor (/ calc 3)) 2))
(defn fuel_req_1
  "Calculates fuel requirements, takes a string sequence"
  [input]
  (apply +
         (map #(calc (Integer/parseInt %)) input)))
(defn calc_2
  "Expects an int, returns the full fuel value"
  [input]
  (apply +
         (loop [x (list input)]
           (if (<= (first x) 0)
             (drop-last (rest x))
             (recur (cons (calc (first x)) x))))))
(defn fuel_req_2
  "Calculates full fuel requirements, takes a string sequence"
  [input]
  (apply +
         (map #(calc_2 (Integer/parseInt %)) input)))

(defn day1
  "Day1"
  [input & args]
  (with-open [rdr (clojure.java.io/reader (str "resources/" input  ".txt"))]
    (println (str "Day 1: " (fuel_req_2 (line-seq rdr))))))