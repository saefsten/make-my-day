const activities = document.querySelectorAll("div.list-group-item")

//show all events
function allEvents(){
    Array.from(activities).forEach((activity) => {
        activity.style.display = 'block';
    })
}

//filter activities
const searchBar = document.getElementById("searchBar")
searchBar.addEventListener('keyup', function(e){
    const term = e.target.value.toLowerCase();
    console.log("keyup")
    Array.from(activities).forEach((activity) => {
        const title = activity.firstElementChild.textContent;
        if(title.toLowerCase().indexOf(e.target.value) != -1) {
            activity.style.display = 'block';
        } else {
            activity.style.display = 'none';
        }
    });
});