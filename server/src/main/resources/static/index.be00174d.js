const e=["ONE","TWO","THREE"],t=document.querySelector(".submit-word"),r=new class{constructor(){this.wordSelectMode=!1,this.selectedItems=[],this.firstSelectedItem,this.gridArea=null}getCellsInRange(e,t){let r=[];if(e.y===t.y){e.x>t.x&&([t,e]=[e,t]);for(let d=e.x;d<=t.x;d++)console.log(this.gridArea.querySelector(`td[data-x="${d}"][data-y="${t.y}"]`)),r.push(this.gridArea.querySelector(`td[data-x="${d}"][data-y="${t.y}"]`))}return r}renderGrid(e,t){var r=document.getElementsByClassName("grid-area")[0];r.lastChild&&r.removeChild(r.lastChild),this.gridArea=r;for(var d=document.createElement("table"),a=document.createElement("tbody"),l=0;l<e;l++){for(var s=document.createElement("tr"),o=0;o<e;o++){var n=document.createElement("td");let e=t[l][o];var i=document.createTextNode(e);n.appendChild(i),n.setAttribute("data-x",l),n.setAttribute("data-y",o),n.setAttribute("data-letter",e),s.appendChild(n)}a.appendChild(s)}d.appendChild(a),r.appendChild(d),r.addEventListener("mousedown",(e=>{this.wordSelectMode=!0;const t=e.target;let r=+t.getAttribute("data-x"),d=+t.getAttribute("data-y"),a=t.getAttribute("data-letter");this.firstSelectedItem={x:r,y:d,letter:a,cell:t}})),r.addEventListener("mouseup",(e=>{this.wordSelectMode=!1,this.selectedItems.forEach((e=>e.cell.classList.remove("selected")))})),r.addEventListener("mousemove",(e=>{if(this.wordSelectMode){const t=e.target;let r=+t.getAttribute("data-x"),d=+t.getAttribute("data-y");t.getAttribute("data-letter");this.getCellsInRange(this.firstSelectedItem,{x:r,y:d}).forEach((e=>e.classList.add("selected")))}}))}},d=new class{displayWord(e){const t=document.querySelector("#word-container");t.innerHTML="";for(let r of e){let e=document.createElement("div");e.innerText=r,t.appendChild(e)}}};t.addEventListener("click",(async()=>{let t=await async function(e,t){let r=await fetch("http://localhost:8080/wordgrid",{method:"POST",body:JSON.stringify({size:e,words:t}),headers:{"Content-type":"application/json; charset=UTF-8"}}),d=await r.json();return d}(20,e),a=t.wordInPuzzle,l=t.contents.map((e=>e.split("")));r.renderGrid(20,l),d.displayWord(a),console.log("Words In Puzzle: ",a),console.log("Contents Array: ",l)}));
//# sourceMappingURL=index.be00174d.js.map
