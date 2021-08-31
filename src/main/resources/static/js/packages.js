const packages = document.querySelectorAll("article.package-list")

const searchBar = document.getElementById("searchBar")
searchBar.addEventListener('keyup', function(e){
    const term = e.target.value.toLowerCase();

    Array.from(packages).forEach((package) => {
        const title = package.firstElementChild.textContent;
        console.log('title ' + title);
        if(title.toLowerCase().indexOf(e.target.value) != -1) {
            package.style.display = 'block';
        } else {
            package.style.display = 'none';
        }
    });
});