const submitWordBtn = document.querySelector(".submit-word");
submitWordBtn.addEventListener("click", async ()=>{
    let result = await fetchGridInfo([
        "ONE",
        "TWO",
        "THREE"
    ]);
    console.log(result);
});
async function fetchGridInfo(wordList) {
    let response = await fetch("http://localhost:8080/wordgrid", {
        method: "POST",
        body: JSON.stringify({
            size: 15,
            words: wordList
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    });
    let result = await response.json();
    return result;
}

//# sourceMappingURL=index.de5c0784.js.map
