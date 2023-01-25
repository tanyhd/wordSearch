export class Word {
    displayWord(words) {
        const wordContainer = document.querySelector("#word-container");
         
        // Clear previous word elements
         wordContainer.innerHTML = "";
         
         // Iterate through words and create elements
         for (let word of words) {
            let div = document.createElement("div");
            div.innerText = word;
            wordContainer.appendChild(div);
        }
    }
}