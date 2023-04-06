interface Nulls {
    allNull: Map<string, Array<string>>;
    someNull: Map<string, Array<string | undefined>>;
    nullOrNot: Array<string | undefined>;
}

export default Nulls;
