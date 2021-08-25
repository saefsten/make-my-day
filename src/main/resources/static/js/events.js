const list = document.querySelector('#activity-list ul')
const activities = list.getElementsByTagName('li');

//show all events
function allEvents(){
    Array.from(activities).forEach((activity) => {
        activity.style.display = 'block';
    })
}

//filter activities
const searchBar = document.forms['search-activities'].querySelector('input');
searchBar.addEventListener('keyup', function(e){
    const term = e.target.value.toLowerCase();
    Array.from(activities).forEach((activity) => {
        const title = activity.firstElementChild.textContent;
        if(title.toLowerCase().indexOf(e.target.value) != -1) {
            activity.style.display = 'block';
        } else {
            activity.style.display = 'none';
        }
    });
});