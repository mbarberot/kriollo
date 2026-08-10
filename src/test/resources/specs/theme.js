document.addEventListener("DOMContentLoaded", () => {
    createHeader()
})

function createHomeLink() {
    return createLink(
        "Home",
        "/Home.html",
        ["primary", "button"],
        () => {
        },
    )
}

function createBackLink() {
    return createLink(
        "Back",
        "#",
        ["primary", "button"],
        () => window.history.back(),
    )
}

function createLink(text, href, classList, onClick) {
    let backLink = document.createElement("a")
    backLink.text = text
    backLink.href = href
    backLink.classList.add(...classList)
    backLink.onclick = onClick

    return backLink
}

function createTitle(textContent ) {
    let project = document.createElement("div")
    project.textContent = textContent
    project.classList.add("title")
    return project;
}

function createHeader() {
    let links = document.createElement("nav")
    links.append(
        createHomeLink(),
        createBackLink(),
    )

    let header = document.createElement("header")
    header.append(
        createTitle("Kriollo"),
        links,
    )

    document.body.insertBefore(header, document.body.firstChild)
}