import type MyOtherType from '../entities/MyOtherType.js';
import type MyEnum from './MyEnum.js';

interface MyType {
    byteArray2D: Array<Array<number>>;
    collection: Array<string>;
    collectionArray: Array<Array<string>>;
    customObject: MyOtherType;
    customObjectArray: Array<MyOtherType>;
    email: string;
    enumType: MyEnum;
    enumTypeArray: Array<MyEnum>;
    intMap: Map<number, number>;
    javaObject: string;
    javaObjectArray: Array<string>;
    minMax: number;
    notBlankEmail: string;
    nullable?: string | undefined;
    objectMap: Map<unknown, unknown>;
    optional?: string | undefined;
    primitive: number;
    primitiveArray: Array<number>;
    stringMap: Map<string, string>;
}

export default MyType;
