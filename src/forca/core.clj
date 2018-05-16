(ns forca.core
  (:gen-class))

(def total-de-vidas 6)

;;função que impressão
(defn perdeu [] (print "Você perdeu"))

;;função que impressão
(defn ganhou [] (print "Você ganhou"))

;;função remove as letras certas da palavra
(defn letras-faltantes [palavra acertos]
  (remove 
    (fn [letra] (contains? acertos (str letra))) 
    palavra
  )
)

;;uma vez vazio, o jogador ganhou
(defn acertou-a-palavra-toda? [palavra acertos]
  (empty? (letras-faltantes palavra acertos))
)

;;pega entrada de dados
(defn le-letra! [] (read-line))

;;o chute tem na palavra?
(defn acertou? [chute palavra] (.contains palavra chute))

;;funcao principal do jogo
(defn jogo [vidas palavra acertos]
  (if (= vidas 0)  ;; se vidas é igual a zero
    (perdeu) ;;você perdeu
    (if (acertou-a-palavra-toda? palavra acertos) ;;senão, acertou a palavra toda?
      (ganhou) ;;você ganhou
      (avalia-chute (le-letra!) vidas palavra acertos) ;;senão, pegue o chute na entrada, verifique e contabilize
    )
  )
)

(defn avalia-chute [chute vidas palavra acertos]
  (if (acertou? chute palavra)
    (jogo vidas palavra (conj acertos chute))
    (jogo (dec vidas) palavra acertos)
  )
)
    
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
