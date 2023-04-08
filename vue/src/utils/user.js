export function getLoginResult() {
    return JSON.parse(localStorage.getItem("login_result") ?? "null")
}
export function setLoginResult(result) {
    return localStorage.setItem("login_result", JSON.stringify(result))
}
export function getToken(){
    return getLoginResult()?.token
}