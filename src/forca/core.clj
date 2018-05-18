  (ns forca.core
    (:gen-class))

  (def total-de-vidas 6)
  (def palavra-secreta "MELANCIA")

  ;;função que impressão
  (defn perdeu [] (println "Você perdeu"))

  ;;função que impressão
  (defn ganhou [] (println "Você ganhou"))

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

  (defn imprime-forca [vidas palavra acertos]
    (println "Vidas " vidas)
      (doseq [letra (seq palavra)] ; loop para executar a função, por ser uma função preguiçosa
        (if(contains? acertos (str letra)) 
          (print letra " ") 
          (print "_" " ")))
    (println))

  ;;funcao principal do jogo
  (defn jogo [vidas palavra acertos]
    (imprime-forca vidas palavra acertos)
    (cond 
      (= vidas 0) (perdeu)
      (acertou-a-palavra-toda? palavra acertos) (ganhou)
      :else
      (let [chute (le-letra!)]
        (if (acertou? chute palavra)
          (do
            (println "Acertou a letra")
            ; (jogo vidas palavra (conj acertos chute))) recursão de calda: quando a última linha da função chama a própria função, 
            (jogo vidas palavra (conj acertos chute))) ; use recur, o compilador fará o empilhamento inteligente
          (do
            (println "Errou a letra! Perdeu vida!")
            ; (jogo (dec vidas) palavra acertos)
            (jogo (dec vidas) palavra acertos))
        )
      )
    )
  )

  (defn comeca-o-jogo [] (jogo total-de-vidas palavra-secreta #{}))
      
  (defn -main
    [& args]
    (comeca-o-jogo))
