/*
 * This function will be invoked when any of this prefab's property is changed
 * @key: property name
 * @newVal: new value of the property
 * @oldVal: old value of the property
 */
function propertyChangeHandler(key, newVal, oldVal) {
    console.log(" ChangeHandler: key " + key + "= " + newVal + ", " + oldVal);
    switch (key) {
        case "start":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                start: [newVal, Prefab.end]
            }, true);
            Prefab.start = newVal;
            break;
        case "end":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                start: [Prefab.start, newVal]
            });
            Prefab.end = newVal;
            break;
        case "min":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                range: {
                    'min': newVal,
                    'max': Prefab.max
                }
            });
            break;
        case "max":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                range: {
                    'min': Prefab.min,
                    'max': newVal
                }
            });
            break;
        case "disable":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            if (newVal) {
                Prefab.handlesSlider.setAttribute('disabled', true);
            } else {
                Prefab.handlesSlider.removeAttribute('disabled');
            }
            break;
        case "step":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                range: {
                    'min': Prefab.min,
                    'max': Prefab.max
                },
                step: newVal,
                start: [Prefab.start, Prefab.end]
            });
            break;
        case "pipdensity":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                pips: {
                    mode: Prefab.pipmode || 'steps',
                    density: newVal,
                    values: Prefab.values
                }
            });
            break;
        case "margin":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                margin: newVal
            });
            break;
        case "padding":
            Prefab.handlesSlider = Prefab.getSlider();
            if (!Prefab.handlesSlider) {
                break;
            }
            Prefab.handlesSlider.noUiSlider.updateOptions({
                padding: newVal
            });
            break;

        default:
            break;

    }
}

Prefab.onReady = function() {
    // this method will be triggered post initialization of the prefab.
    Prefab.handlesSlider = Prefab.getSlider();
    Prefab.handlesSlider.noUiSlider.on('start', function(stringValues, index, absValues) {
        Prefab.onStart(stringValues, index, absValues);
    });
    Prefab.handlesSlider.noUiSlider.on('end', function(stringValues, index, absValues) {
        Prefab.onEnd(stringValues, index, absValues);
    });
    Prefab.handlesSlider.noUiSlider.on('change', function(stringValues, index, absValues) {
        // set the values to outbound prefab properties
        Prefab.start = absValues[0];
        Prefab.end = absValues[1];
        Prefab.onHandlechange(stringValues, index, absValues);
    });
};

Prefab.getSlider = function() {
    if (Prefab.handlesSlider && Prefab.handlesSlider.noUiSlider) {
        return Prefab.handlesSlider;
    } else {
        if ((Prefab.step || Prefab.step === 0) && (Prefab.start || Prefab.start === 0) &&
            (Prefab.end || Prefab.end === 0) && (Prefab.min || Prefab.min === 0) && (Prefab.max || Prefab.max === 0)) {
            Prefab.handlesSlider = document.getElementsByName('noui-range-slider')[0];
            if (!Prefab.handlesSlider) {
                return;
            }
            noUiSlider.create(Prefab.handlesSlider, {
                start: [Prefab.start, Prefab.end],
                connect: true,
                step: Prefab.step,
                behaviour: 'tap',
                range: {
                    min: [Prefab.min],
                    max: [Prefab.max]
                },
                pips: {
                    mode: Prefab.pipmode,
                    density: Prefab.pipdensity,
                    values: Prefab.values
                },
                tooltips: true,
                margin: Prefab.margin,
                padding: Prefab.padding
            });
            return Prefab.handlesSlider;
        } else {
            return;
        }
    }
};

/* register the property change handler */
Prefab.onPropertyChange = propertyChangeHandler;

Prefab.filterPips = function(value, type) {
    return value % Prefab.step ? 1 : 2;
};
