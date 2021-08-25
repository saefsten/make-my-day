/*function handleDragStart(e) {
    this.style.opacity = '0.4';
    dragged = event.target;
    console.log(dragged)
}

function handleDragEnd(e) {
    this.style.opacity = '1';
    console.log('Drag ended')
    event.preventDefault();
    // move dragged elem to the selected drop target
    if ( event.target.className == "row" ) {
        event.target.style.background = "";
        dragged.parentNode.removeChild( dragged );
        event.target.appendChild( dragged );
    }
}

let items = document.querySelectorAll('.row');
var dragged;
items.forEach(function(item) {
    item.addEventListener('dragstart', handleDragStart, false);
    item.addEventListener('dragend', handleDragEnd, false);
    item.addEventListener('drop', handleDrop, false);
});

function handleDrop(e) {
    console.log('Dropped')
      // prevent default action (open as link for some elements)
      event.preventDefault();
      // move dragged elem to the selected drop target
      if ( event.target.className == "row" ) {
          event.target.style.background = "";
          dragged.parentNode.removeChild( dragged );
          event.target.appendChild( dragged );
      }
  }*/

  var dragged;

    /* events fired on the draggable target */
    document.addEventListener("drag", function( event ) {

    }, false);

    document.addEventListener("dragstart", function( event ) {
        // store a ref. on the dragged elem
        dragged = event.target;
        // make it half transparent
        event.target.style.opacity = .5;
    }, false);

    document.addEventListener("dragend", function( event ) {
        // reset the transparency
        event.target.style.opacity = "";
    }, false);

    /* events fired on the drop targets */
    document.addEventListener("dragover", function( event ) {
        // prevent default to allow drop
        event.preventDefault();
    }, false);

    document.addEventListener("dragenter", function( event ) {
        // highlight potential drop target when the draggable element enters it
        if ( event.target.className == "row event-row" ) {
            event.target.style.background = "#e2dedb";
        }

    }, false);

    document.addEventListener("dragleave", function( event ) {
        // reset background of potential drop target when the draggable element leaves it
        if ( event.target.className == "row event-row" ) {
            event.target.style.background = "";
        }

    }, false);

    document.addEventListener("drop", function( event ) {
        // prevent default action (open as link for some elements)
        event.preventDefault();
        // move dragged elem to the selected drop target
        console.log(dragged.id)
        if ( event.target.className == "row event-row" ) {
            if (dragged.id.includes("eventBox")) {
                console.log(dragged)
                dragged = addDraggableDiv(dragged)
            } else {
                dragged.parentNode.removeChild( dragged );
            }
            event.target.style.background = "";
            event.target.appendChild( dragged );
            event.target.querySelector(".event-time h4").innerText = event.target.querySelector(".time-stamp").innerText
        }

    }, false);

    function addDraggableDiv(dragged) {
        let newDraggable = document.createElement("div")
        newDraggable.classList.add("col")
        newDraggable.classList.add("event-section")
        newDraggable.setAttribute('draggable', true)

        const duration = parseInt(dragged.querySelector("small").innerText.substring(0, dragged.querySelector("small").innerText.indexOf(" ")))

        switch(duration) {
            case 1:
                newDraggable.classList.add("event-1h")
                break
            case 2:
                newDraggable.classList.add("event-2h")
                break
            case 3:
                newDraggable.classList.add("event-3h")
                break
            case 4:
                newDraggable.classList.add("event-4h")
                break
            default:
                newDraggable.classList.add("event-1h")
                break
        }

        let firstChild = document.createElement("div")
        firstChild.classList.add("event-time")

        let timeChild = document.createElement("div")
        let timeHead = document.createElement("h4")
        timeHead.innerText = "--:--"

        let descriptionChild = document.createElement("div")
        descriptionChild.classList.add("event-description")
        let description = document.createElement("strong")
        description.innerText = dragged.querySelector("h5").innerText
        let paragraph = document.createElement("p")
        let siteLink = document.createElement("a")
        if (dragged.querySelector(".link-info") != null) {
            siteLink.setAttribute("href", dragged.querySelector(".link-info"))
            siteLink.innerText = dragged.querySelector(".link-info").innerText
            siteLink.classList.add("link-info")
            paragraph.appendChild(siteLink)
        }

        let button = document.createElement("button")
        button.classList.add("delete-btn")
        button.setAttribute('onclick', 'clickedDelete(this)')
        button.innerText = 'x'

        // Append all in correct order
        timeChild.appendChild(timeHead)
        firstChild.appendChild(timeChild)

        descriptionChild.appendChild(button)
        descriptionChild.appendChild(description)
        descriptionChild.appendChild(paragraph)

        newDraggable.appendChild(firstChild)
        newDraggable.appendChild(descriptionChild)

        return newDraggable
    }

    function clickedDelete(button) {
        const element = button.parentElement.parentElement
        element.parentElement.removeChild(element)
    }