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
        if ( event.target.className == "row" ) {
            event.target.style.background = "purple";
        }

    }, false);

    document.addEventListener("dragleave", function( event ) {
        // reset background of potential drop target when the draggable element leaves it
        if ( event.target.className == "row" ) {
            event.target.style.background = "";
        }

    }, false);

    document.addEventListener("drop", function( event ) {
        // prevent default action (open as link for some elements)
        event.preventDefault();
        // move dragged elem to the selected drop target
        if ( event.target.className == "row event-row" ) {
            event.target.style.background = "";
            dragged.parentNode.removeChild( dragged );
            event.target.appendChild( dragged );
            event.target.querySelector(".event-time h4").innerText = event.target.querySelector(".time-stamp").innerText
        }

    }, false);