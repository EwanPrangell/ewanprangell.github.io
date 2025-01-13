let header = document.querySelector('#load');
let anim = [
    { t: " ", ms: 200 },
    { t: "_", ms: 200 },
    { t: " ", ms: 200 },
    { t: "_", ms: 200 },
    { t: "E_", ms: 300 },
    { t: "EW_", ms: 300 },
    { t: "EWA_", ms: 300 },
    { t: "EWAN_", ms: 300 },
    { t: "EWAN P_", ms: 300 },
    { t: "EWAN PR_", ms: 300 },
    { t: "EWAN PRA_", ms: 300 },
    { t: "EWAN PRAN_", ms: 300 },
    { t: "EWAN PRANG_", ms: 300 },
    { t: "EWAN PRANGE_", ms: 300 },
    { t: "EWAN PRANGEL_", ms: 300 },
    { t: "EWAN PRANGELL_", ms: 300 },
    { t: "EWAN PRANGELL_", ms: 400 },
    { t: "EWAN PRANGELL ", ms: 400 },
    { t: "EWAN PRANGELL_", ms: 400 },
    { t: "EWAN PRANGELL ", ms: 400 },
    { t: "EWAN PRANGELL_", ms: 400 },
    { t: "EWAN PRANGELL", ms: 400 },
    { t: "EWAN PRANGELL", ms: 300 }
];
let stepDenominator = 1;
if (window.localStorage.stepDenominator)
    stepDenominator = window.localStorage.stepDenominator;
let i = 0;
let update = () => {
    let step = anim[i];
    header.innerText = step.t;
    i++;

    if (i < anim.length)
        setTimeout(update, step.ms / stepDenominator);
    else {
        header.classList.add('top');
        setTimeout(() => {
            document.getElementById('main').style.opacity = 1;
            initGlobe();
        }, 500);
        window.localStorage.stepDenominator = 2;
    }
}
update();