window.onload = function () {
    const rangeInputs = document.getElementsByClassName('slider')
    const autoSendSelect = document.getElementsByClassName('auto-send-select')


    function handleInputChange(e) {
        let target = e.target
        if (e.target.type !== 'range') {
            target = document.getElementById('range')
        }
        const min = target.min
        const max = target.max
        const val = target.value

        target.style.backgroundSize = (val - min) * 100 / (max - min) + '% 100%'
    }


    for (let i = 0; i < rangeInputs.length; i++) {
        element = rangeInputs.item(i)
        element.addEventListener('input', handleInputChange)
    }

    for (let i = 0; i < autoSendSelect.length; i++)
        autoSendSelect.item(i).addEventListener(`change`, e => {
        const $select = e.target;

        if (!$select.classList.contains(`auto-send-select`)) return;

        const body = new FormData();
        body.set($select.name, $select.value);
        // for (var pair of body.entries()) {
        //     console.log(pair[0]+ ', ' + pair[1]);
        // }
        e.target.disabled = true;
        const formNode = e.target.parentNode;
        fetch($(formNode).attr('action'), {
            method: `post`,
            body: body
        })
            .catch(err => {
                console.error(err);
            })
            .finally(() => {
                $select.disabled = false;
            });
    });

}

