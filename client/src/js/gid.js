export class Grid {

    constructor() {
        this.wordSelectMode = false;
        this.selectedItems = [];
        this.firstSelectedItem;
        this.gridArea = null;
    }

    getCellsInRange(firstLetter, currentLetter) {
        let cellsInRange = [];
        if (firstLetter.y === currentLetter.y) {
            if (firstLetter.x > currentLetter.x) {
                [currentLetter, firstLetter] = [firstLetter, currentLetter];
            }
            for (let i = firstLetter.x; i <= currentLetter.x; i++) {
                console.log(this.gridArea.querySelector(`td[data-x="${i}"][data-y="${currentLetter.y}"]`));
                cellsInRange.push(this.gridArea.querySelector(`td[data-x="${i}"][data-y="${currentLetter.y}"]`));
            }
        }
        return cellsInRange;
    }

    renderGrid(gridSize, wordgrid) {
        var gridArea = document.getElementsByClassName("grid-area")[0];
        if (gridArea.lastChild) {
            gridArea.removeChild(gridArea.lastChild);
        }
        this.gridArea = gridArea;
        var tbl = document.createElement("table");
        var tblBody = document.createElement("tbody");

        for (var i = 0; i < gridSize; i++) {
            var row = document.createElement("tr");

            for (var j = 0; j < gridSize; j++) {
                var cell = document.createElement("td");
                let letter = wordgrid[i][j];
                var cellText = document.createTextNode(letter);
                cell.appendChild(cellText);
                cell.setAttribute("data-x", i);
                cell.setAttribute("data-y", j);
                cell.setAttribute("data-letter", letter);
                row.appendChild(cell);
            }
            tblBody.appendChild(row);
        }
        tbl.appendChild(tblBody);
        gridArea.appendChild(tbl);

        // Click Handlers
        gridArea.addEventListener("mousedown", (event) => {
            this.wordSelectMode = true;
            const cell = event.target;
            let x = +cell.getAttribute("data-x");
            let y = +cell.getAttribute("data-y");
            let letter = cell.getAttribute("data-letter");
            this.firstSelectedItem = {
                x, y, letter, cell
            };
        });

        gridArea.addEventListener("mouseup", (event) => {
            this.wordSelectMode = false;
            this.selectedItems.forEach(item => item.cell.classList.remove("selected"));

        })

        gridArea.addEventListener("mousemove", (event) => {
            if (this.wordSelectMode) {
                const cell = event.target;
                let x = +cell.getAttribute("data-x");
                let y = +cell.getAttribute("data-y");
                let letter = cell.getAttribute("data-letter");

                this.getCellsInRange(this.firstSelectedItem, {x, y})
                .forEach(cell => cell.classList.add("selected"));
            }
        })
    }
} 