const activities = document.querySelectorAll("div.search-dropdown")

const searchBar = document.getElementById("searchBar")
searchBar.addEventListener('keyup', function(e){
    if (searchBar.value == "") {
        Array.from(activities).forEach((activity) => {
            activity.style.display = 'none';
        })
        return;
    } else {
        document.getElementById("dropdown-id").classList.remove("hidden");
    }
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