(ns bowling-kata.core-test
  (:require [clojure.test :refer :all]
            [bowling-kata.core :as bowling]))

;; Defines the rolls and scores per frame.  Intermediate scores are what
;; would appear if a bonus had not been finalized yet and score incorporates
;; the bonos
(def results [{:rolls [1 4]   :score 5   :intermediate_score 5}
              {:rolls [4 5]   :score 14  :intermediate_score 14}
              {:rolls [6 4]   :score 29  :intermediate_score 24}
              {:rolls [5 5]   :score 49  :intermediate_score 39}
              {:rolls [10]    :score 60  :intermediate_score 59}
              {:rolls [0 1]   :score 61  :intermediate_score 61}
              {:rolls [7 3]   :score 77  :intermediate_score 71}
              {:rolls [6 4]   :score 97  :intermediate_score 87}
              {:rolls [10]    :score 117 :intermediate_score 107}
              {:rolls [2 8 6] :score 133 :intermediate_score 133}])

(defn score-and-rolls-for-frame
  "For the specified frame, retrives the intermediate score, current rolls and previous frames"
  [data frame]
  (let [frame-index (dec frame)
        history (map :rolls (take frame-index data))
        {score :intermediate_score rolls :rolls} (nth data frame-index)]
      {:score score
       :rolls rolls
       :history history}))

(deftest calculate-score-test
  (dotimes [n 10]
   (let [frame (inc n)]
    (testing (str "Frame: " frame)
      (let [{score :score rolls :rolls history :history}
            (score-and-rolls-for-frame results frame)]
        (is (= score (bowling/calculate-score history rolls))))))))
