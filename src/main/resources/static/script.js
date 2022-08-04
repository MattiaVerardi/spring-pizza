const addButton = document.getElementById("btn");
addButton.addEventListener("click", addPizza);

function addPizza() {
  axios
    .get("http://localhost:8080/api/pizza")
    .then(function (response) {
      for (let i = 0; i < response.data.length; i++) {
        createPizza(response.data[i]);
      }
      //const pizza = response.data[0];
      // createPizza(pizza);
    })
    .catch(function (error) {
      console.log("errore");
    });
}

function createPizza(pizza) {
  let nome = document.createElement("p");
  nome.innerText = pizza.nome;
  let descrizione = document.createElement("p");
  descrizione.innerText = pizza.descrizione;
  let prezzo = document.createElement("p");
  prezzo.innerText = pizza.prezzo;

  document.getElementById("nome").appendChild(nome);
  document.getElementById("prezzo").appendChild(prezzo);
  document.getElementById("descrizione").appendChild(descrizione);
}
