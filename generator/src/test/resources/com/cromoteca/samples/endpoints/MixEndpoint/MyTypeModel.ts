import type MyOtherType from '../entities/MyOtherType.js';
import MyOtherTypeModel from '../entities/MyOtherTypeModel.js';
import type MyEnum from './MyEnum.js';
import MyEnumModel from './MyEnumModel.js';
import type MyType from './MyType.js';
import { ArrayModel, Email, Max, Min, NotBlank, NumberModel, ObjectModel, StringModel, _getPropertyModel } from '@hilla/form';

class MyTypeModel<T extends MyType = MyType> extends ObjectModel<T> {
    declare static createEmptyValue: () => MyType;

    get byteArray2D(): ArrayModel<ReadonlyArray<number>, ArrayModel<number, NumberModel>> {
        return this[_getPropertyModel]('byteArray2D', ArrayModel, [false, ArrayModel, [false, NumberModel, [false]]]) as ArrayModel<ReadonlyArray<number>, ArrayModel<number, NumberModel>>;
    }

    get collection(): ArrayModel<string, StringModel> {
        return this[_getPropertyModel]('collection', ArrayModel, [false, StringModel, [false]]) as ArrayModel<string, StringModel>;
    }

    get collectionArray(): ArrayModel<ReadonlyArray<string>, ArrayModel<string, StringModel>> {
        return this[_getPropertyModel]('collectionArray', ArrayModel, [false, ArrayModel, [false, StringModel, [false]]]) as ArrayModel<ReadonlyArray<string>, ArrayModel<string, StringModel>>;
    }

    get customObject(): MyOtherTypeModel {
        return this[_getPropertyModel]('customObject', MyOtherTypeModel, [false]) as MyOtherTypeModel;
    }

    get customObjectArray(): ArrayModel<MyOtherType, MyOtherTypeModel> {
        return this[_getPropertyModel]('customObjectArray', ArrayModel, [false, MyOtherTypeModel, [false]]) as ArrayModel<MyOtherType, MyOtherTypeModel>;
    }

    get email(): StringModel {
        return this[_getPropertyModel]('email', StringModel, [false, new Email()]) as StringModel;
    }

    get enumType(): MyEnumModel {
        return this[_getPropertyModel]('enumType', MyEnumModel, [false]) as MyEnumModel;
    }

    get enumTypeArray(): ArrayModel<MyEnum, MyEnumModel> {
        return this[_getPropertyModel]('enumTypeArray', ArrayModel, [false, MyEnumModel, [false]]) as ArrayModel<MyEnum, MyEnumModel>;
    }

    get intMap(): ObjectModel<Record<string, number>> {
        return this[_getPropertyModel]('intMap', ObjectModel, [false]) as ObjectModel<Record<string, number>>;
    }

    get javaObject(): StringModel {
        return this[_getPropertyModel]('javaObject', StringModel, [false]) as StringModel;
    }

    get javaObjectArray(): ArrayModel<string, StringModel> {
        return this[_getPropertyModel]('javaObjectArray', ArrayModel, [false, StringModel, [false]]) as ArrayModel<string, StringModel>;
    }

    get minMax(): NumberModel {
        return this[_getPropertyModel]('minMax', NumberModel, [false, new Min(4), new Max(10)]) as NumberModel;
    }

    get notBlankEmail(): StringModel {
        return this[_getPropertyModel]('notBlankEmail', StringModel, [false, new NotBlank(), new Email()]) as StringModel;
    }

    get nullable(): StringModel {
        return this[_getPropertyModel]('nullable', StringModel, [true]) as StringModel;
    }

    get objectMap(): ObjectModel<Record<string, unknown>> {
        return this[_getPropertyModel]('objectMap', ObjectModel, [false]) as ObjectModel<Record<string, unknown>>;
    }

    get optional(): StringModel {
        return this[_getPropertyModel]('optional', StringModel, [true]) as StringModel;
    }

    get primitive(): NumberModel {
        return this[_getPropertyModel]('primitive', NumberModel, [false]) as NumberModel;
    }

    get primitiveArray(): ArrayModel<number, NumberModel> {
        return this[_getPropertyModel]('primitiveArray', ArrayModel, [false, NumberModel, [false]]) as ArrayModel<number, NumberModel>;
    }

    get stringMap(): ObjectModel<Record<string, string>> {
        return this[_getPropertyModel]('stringMap', ObjectModel, [false]) as ObjectModel<Record<string, string>>;
    }
}

export default MyTypeModel;
