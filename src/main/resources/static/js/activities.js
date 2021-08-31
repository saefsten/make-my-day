

function activitiesFilter() {
        const cat = document.getElementById("category").value
        const activities = document.querySelectorAll("article")
        Array.from(activities).forEach((activity) => {
            if(activity.querySelector("." + cat) !== null || cat === "All categories") {
                activity.style.display = 'block';
            } else {
                activity.style.display = 'none';
            }
        });
    }
