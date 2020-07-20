export const CHANGE = "CHANGE";


export const change = pagecount => {
    return {
        type: CHANGE,
        pagecount
    }
}