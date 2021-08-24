const list = document.querySelector('#activity-list ul')

//delete activities

//add activities

//hide activities

//filter activities
const searchBar = document.forms['search-activities'].querySelector('input');
searchBar.addEventListener('keyup', function(e){
    const term = e.target.value.toLowerCase();
    const activities = list.getElementsByTagName('li');
    Array.from(activities).forEach((activity) => {
        const title = activity.firstElementChild.textContent;
        if(title.toLowerCase().indexOf(e.target.value) != -1) {
            activity.style.display = 'block';
        } else {
            activity.style.display = 'none';
        }
    });
});