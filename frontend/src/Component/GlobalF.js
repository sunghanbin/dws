

export const isEmpty = (value) => {
    if (value == "" || value == null || value == undefined ||
        (value != null && typeof value == "object" && !Object.keys(value).length)) {
        return true;
    } else {
        return false;
    }
}


export const siguSearch = (array, condition) => {
    let head = 0;
    let tail = array.length - 1;
    const resurltArr = [];

    let center = Math.round((head + tail) / 2);

    while (array[center].city_cd !== condition) {

        if (head > tail) return "지역정보없음";

        if (array[center].city_cd < condition) {

            head = center + 1;
            center = Math.round((head + tail) / 2);
        } else {
            tail = center - 1;
            center = Math.round((head + tail) / 2);
        }
    }

    resurltArr[0] = array[center];
    if (center !== array.length - 1) {
        for (var i = 1; i < array.length; i++) {
            if (array[center + i].city_cd === condition) {

                resurltArr[i] = array[center + i];
            }
            else {
                break;
            }
        }
    }
    let t = 1;
    if (center !== 0) {
        for (var j = resurltArr.length; j < array.length; j++) {

            if (center - t !== 0) {

                if (array[center - t].city_cd === condition) {
                    resurltArr[j] = array[center - t];
                    t += 1;

                }
                else {
                    break;
                }
            } else {
                break;
            }
        }
    }
    resurltArr.sort((f, r) => {
        return f.city_cd < r.city_cd ? -1 : 1
    })

    return resurltArr;
}