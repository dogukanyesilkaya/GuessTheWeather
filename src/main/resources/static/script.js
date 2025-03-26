let guessesLeft=-1;
let randomTemp="";

window.onload=async function(){
    await resetGame();
};

async function resetGame(){
    document.getElementById("difficulty").value="moderate";
    setGuesses("moderate")
    randomTemp=await getRandomTemperature();
    hideElements();
}

document.getElementById("guessButton").addEventListener("click", async function() {
    const code = document.getElementById("search").value.trim();
    console.log("Search result: "+code);
    if (code === "") {
        document.getElementById("result").textContent = "";
        return;
    }

    checkGuessAccuracy(guessesLeft);

    try {
        const response = await fetch(`http://localhost:8080/country?code=${encodeURIComponent(code)}`);

        await response.text().then(temperature => {
            const temperatureInt = parseInt(temperature);
            const randomtempInt = parseInt(randomTemp);
            const compareInt = randomtempInt-temperatureInt;
            const compareText = compareInt.toString();
            guessesLeft-=1;


            document.getElementById("result").textContent = "Temperature of your selection is "+temperature+" You are this close to target "+compareText;
            document.getElementById("guessesleft").textContent = `You have ${guessesLeft} left`;

            checkGuessAccuracy(guessesLeft,compareInt);
        })

    } catch (error) {
        document.getElementById("result").textContent = "Error fetching data";
    }
});


document.getElementById("difficulty").addEventListener("change", async function(event) {
    randomTemp = await getRandomTemperature();
    setGuesses(event.target.value);
})

document.getElementById("retryButton").addEventListener("click", async function() { //randomTemp is not being assigned
    await resetGame();
})