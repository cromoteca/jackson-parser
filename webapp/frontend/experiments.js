const map = new Map();
map.set(1, 'a');
map.set({a: 3, b: true}, 'b');
map.set(3, {c: 4, d: true});
console.log(map);
const json = JSON.stringify(Array.from(map.entries()));
console.log(json);

const mapEntries = JSON.parse(json);
// test if "mapEntries" can be parsed back to a Map
if (mapEntries instanceof Array && mapEntries.every(entry => entry instanceof Array && entry.length === 2)) {
    const map2 = new Map(mapEntries);
    console.log(map2);
    console.log(map.get(3) === map2.get(3));
}

const json2 = '{"@type": "Map", "@entries": [[1,"a"],[{"a":3,"b":true},"b"],[3,{"c":4,"d":true}]]}';
const map3 = JSON.parse(json2, (_key, value) => {
    if (value && value['@type'] === 'Map') {
        return new Map(value['@entries']);
    }
    return value;
});
console.log(map3);

console.log(map.get(3) === map3.get(3));
