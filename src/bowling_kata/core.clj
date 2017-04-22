(ns bowling-kata.core)

(defn is-strike? [frame]
  (some #{10} frame))

(defn is-spare? [frame]
  (and (not (is-strike? frame))
       (= (apply + frame) 10)))

(defn bonus-for-frame [frame next-frame]
  (if (is-strike? frame)
    (apply + (take 2 next-frame))
    (if (is-spare? frame)
        (apply + (take 1 next-frame))
        0)))

(defn calculate-bonuses [frames]
  (map-indexed (fn [index frame]
                 (if (< index (dec (count frames)))
                    (bonus-for-frame frame (nth frames (inc index)))
                    0)) ; next-frame is not available, so return no bonus
               frames))

(defn calculate-score
  "Given previous rolls and the current rolls calculates the score"
  [history rolls]
  (apply + (concat rolls
                   (flatten history)
                   (calculate-bonuses (concat history [rolls])))))
