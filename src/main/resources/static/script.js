let guessesLeft=-1;
let randomTemp="";

window.onload=async function(){
    await resetGame();
};

async function resetGame(){
    document.getElementById("difficulty").value="moderate";
    setGuesses("moderate")
    hideElements();

}

function hideElements(){
    document.getElementById("search").value = "";
    document.getElementById("randomtemp").value = `Random Temperature: `;
    document.getElementById("result").textContent = "";
}

function setGuessLeftText(guessesLeft){
    document.getElementById("guessesleft").textContent = `You have ${guessesLeft} guesses left`;
}

function checkGuessAccuracy(guessesLeft,compareInt){
    if(compareInt === 0){
        //game won retry popup
        document.getElementById("popup").getElementsByTagName('p')[0].textContent = "Congrats you won the game!!";
        document.getElementById("popup").style.display = "block";
        document.getElementById("overlay").style.display = "block";
        return;
    }
    if(guessesLeft === 0){
        //game lost retry popup
        document.getElementById("popup").getElementsByTagName('p')[0].textContent = "You've lost the game.";
        document.getElementById("popup").style.display = "block";
        document.getElementById("overlay").style.display = "block";
    }

}

function setGuesses(diff){
    switch(diff.toLowerCase()){
        case "easy":
            guessesLeft=5;
            break;
        case "moderate":
            guessesLeft=3;
            break;
        case "hard":
            guessesLeft=2;
            break;
        case "extreme":
            guessesLeft=1;
            break;
        default:
            return ;
    }

    setGuessLeftText(guessesLeft);
}

async function getRandomTemperature() {
    try {
        const response = await fetch("http://localhost:8080/country-randomtemp");
        const randomtemp = await response.text();
        document.getElementById("randomtemp").textContent = `Random Temperature: ${randomtemp}`;
        return randomtemp;
    } catch (error) {
        return null;
    }
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
            const randomtempInt = parseInt(randomtemp);
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
    randomtemp = await getRandomTemperature();
    setGuesses(event.target.value);
})

document.getElementById("retryButton").addEventListener("click", async function() { //randomTemp is not being assigned
    randomTemp= await getRandomTemperature();
    await resetGame();
})