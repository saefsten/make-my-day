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
        if ( event.target.className == "row event-row" ) {
            if (dragged.id.includes("eventBox")) {
                dragged = addDraggableDiv(dragged)
            } else {
                dragged.parentNode.removeChild( dragged );
            }
            event.target.style.background = "";

            event.target.appendChild( dragged );
            event.target.querySelector(".event-time h4").innerText = event.target.querySelector(".time-stamp").innerText

            updateDistances()
        }

    }, false);

    function addDraggableDiv(dragged) {
        let newDraggable = document.createElement("div")
        newDraggable.classList.add("col")
        newDraggable.classList.add("event-section")
        newDraggable.setAttribute('draggable', true)
        let timeStampId = "timeStamp" + dragged.id.substring(8)
        newDraggable.setAttribute('id', timeStampId)

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
            case 5:
                newDraggable.classList.add("event-5h")
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
        updateDistances()
    }

    function clickedSave() {
        const elements = document.querySelectorAll("div.event-section")
        if (elements.length === 0) {
            alert("You need to have at least one activity in order to save")
            return
        }
        const date = document.getElementById("date").value
        if (document.getElementById("date").value === "") {
            alert("Please select a date before saving.")
            return
        }

        for (element of elements) {
            const time = element.querySelector("h4").innerText
            const id = element.id.substring(9)

            let el = document.createElement("input")
            el.setAttribute('type', 'hidden')
            el.setAttribute('name', 'events')
            el.setAttribute('value', `${time}-${id}`)
            document.getElementById("eventForm").appendChild(el)
        }

        document.getElementById("eventForm").submit()
    }

    function changeCategoryFilter() {
        const cat = document.getElementById("categoryFilter").value
        Array.from(activities).forEach((activity) => {
            if(activity.querySelector("." + cat) !== null || cat === "All categories") {
                activity.style.display = 'block';
            } else {
                activity.style.display = 'none';
            }
        });
    }

    function getDistanceFromLatLonInMeters(lat1, lon1, lat2, lon2) {
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2-lat1);  // deg2rad below
        var dLon = deg2rad(lon2-lon1);
        var a =
            Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
        ;
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = R * c; // Distance in km
        return Math.round(d * 1000); // Return distance in whole meters round up
        //return d * 1000
    }

    function deg2rad(deg) {
        return deg * (Math.PI/180)
    }

    function updateDistances() {
        const eventParagraphs = document.querySelectorAll(".event-section")
        const originLatLong = getActivityLatLong(eventParagraphs[0].id)
        // If there is only one event, the list should be sorted accordingly
        if (eventParagraphs.length === 1) {
            sortEventListByDistance(originLatLong[0], originLatLong[1])
        }

        // Distances should be calculated only if more than one event exists
        if (eventParagraphs.length > 1) {
            let shortestDistanceFromOrigin = -1
            let closestEvent
            for (let i = 1; i < eventParagraphs.length; i++) {
                const prevLatLong = getActivityLatLong(eventParagraphs[i-1].id)
                const curLatLong = getActivityLatLong(eventParagraphs[i].id)

                const meters = getDistanceFromLatLonInMeters(prevLatLong[0], prevLatLong[1], curLatLong[0], curLatLong[1])
                eventParagraphs[i].querySelector("p").innerText = "From " + eventParagraphs[i-1].querySelector("strong").innerText + ": " + meters + " m"

                const fromOrigin = getDistanceFromLatLonInMeters(originLatLong[0], originLatLong[1], curLatLong[0], curLatLong[1])
                if (fromOrigin < shortestDistanceFromOrigin || shortestDistanceFromOrigin === -1) {
                    shortestDistanceFromOrigin = fromOrigin
                    closestEvent = eventParagraphs[i].querySelector("strong").innerText
                }
            }
            eventParagraphs[0].querySelector("p").innerText = "Closest following event: " + closestEvent

            const lastLatLong = getActivityLatLong(eventParagraphs[eventParagraphs.length-1].id)
            sortEventListByDistance(lastLatLong[0], lastLatLong[1])
        }
    }

    function getActivityLatLong(eventId) {
        const id = eventId.substring(9)
        const lat = document.getElementById("eventBox" + id).querySelector(".latitude").classList[1]
        const long = document.getElementById("eventBox" + id).querySelector(".longitude").classList[1]
        return [parseFloat(lat), parseFloat(long)]
    }

    function sortEventListByDistance(lat, long) {
        const list = document.querySelectorAll(".list-group-item")
        var listArr = []
        for (item of list) {
            listArr.push(item)
        }
        listArr.sort(function(a, b) {
            const aLat = parseFloat(a.querySelector(".latitude").classList[1])
            const aLong = parseFloat(a.querySelector(".longitude").classList[1])
            const bLat = parseFloat(b.querySelector(".latitude").classList[1])
            const bLong = parseFloat(b.querySelector(".longitude").classList[1])

            const aDist = getDistanceFromLatLonInMeters(lat, long, aLat, aLong)
            const bDist = getDistanceFromLatLonInMeters(lat, long, bLat, bLong)

            return aDist-bDist
        });
        document.getElementById("activity-list").innerHTML = ""
        for (let i = 0; i < listArr.length; i++) {
            document.getElementById("activity-list").appendChild(listArr[i])
        }

        /*Array.from(activities).forEach((activity) => {
            if(activity.querySelector("." + cat) !== null || cat === "All categories") {
                activity.style.display = 'block'
            } else {
                activity.style.display = 'none'
            }
        });*/
    }