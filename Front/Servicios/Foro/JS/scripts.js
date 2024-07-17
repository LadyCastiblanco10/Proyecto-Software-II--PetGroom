document.addEventListener("DOMContentLoaded", function () {
    let currentImageIndex = 0;
    const images = document.querySelectorAll(".hero-image img");
    const totalImages = images.length;

    function changeImage() {
        images[currentImageIndex].classList.remove("active");
        currentImageIndex = (currentImageIndex + 1) % totalImages;
        images[currentImageIndex].classList.add("active");
    }

    setInterval(changeImage, 3000); // Cambiar imagen cada 3 segundos
});

function toggleMessage(button) {
    const messageDiv = button.parentElement.nextElementSibling;
    const userInfoDiv = messageDiv.nextElementSibling;
    if (messageDiv.style.display === "none" || messageDiv.style.display === "") {
        messageDiv.style.display = "block";
        userInfoDiv.style.display = "flex";
    } else {
        messageDiv.style.display = "none";
        userInfoDiv.style.display = "none";
    }
}
