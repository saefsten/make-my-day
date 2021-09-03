const activities = document.querySelectorAll("div.list-group-item")

//show all events
function allEvents(){
    document.getElementById("categoryFilter").value = 'All categories'
    Array.from(activities).forEach((activity) => {
        activity.style.display = 'block';
    })
}

//filter activities
const searchBar = document.getElementById("searchBar")
searchBar.addEventListener('keyup', function(e){
    const term = e.target.value.toLowerCase();
    Array.from(activities).forEach((activity) => {
        const title = activity.firstElementChild.textContent;
        if(title.toLowerCase().indexOf(term) != -1) {
            activity.style.display = 'block';
        } else {
            activity.style.display = 'none';
        }
    });
});