(ns aoc-clojure.day2)

(defn get-opcodes
  "Splits the input string into its numbers"
  [input]
  (mapv #(Integer/parseInt %) (clojure.string/split (slurp (str "resources/day2.txt")) #",")))

(defn apply-opcode
  "Applies the operation over the next three indexes in input"
  [operation index input]
  (assoc input (get input (+ index 3)) (operation (get input (get input (+ index 1))) (get input (get input (+ index 2))))))

(defn eval-opcode
  "Evaluates one opcode and applies it to the list of ints"
  [index code input]
  (case code
    1 (apply-opcode + index input)
    2 (apply-opcode * index input)))

(defn process-opcodes
  "Process a given list of opcode ints"
  [input]
  (loop [index 0 opcodes input]
    (let [code (get opcodes index)]
     (if (= code 99)
       opcodes
     (recur (+ index 4) (eval-opcode index (get opcodes index) opcodes))))))
(defn apply-components
  "Applies the components"
  [noun verb input]
  (assoc input 1 noun 2 verb))

(defn find-components
  "Process a given list of opcode ints"
  [input]
  (some identity (for [noun (range 100) verb (range 100)]
      (if
        (= (get (process-opcodes (apply-components noun verb input)) 0) 19690720)
        (+ (* 100 noun) verb)))))

;for pt. 2: loop until fits
(defn day2
  "Day2"
  [input]
  (println "Day 2:")
  (println (process-opcodes (get-opcodes input)))
  (println (find-components (get-opcodes input))))