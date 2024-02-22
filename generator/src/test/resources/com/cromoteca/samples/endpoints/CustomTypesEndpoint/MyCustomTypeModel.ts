import type MyCustomType from './MyCustomType.js';
import { ObjectModel, StringModel, _getPropertyModel } from '@vaadin/hilla-lit-form';

class MyCustomTypeModel<T extends MyCustomType = MyCustomType> extends ObjectModel<T> {
    declare static createEmptyValue: () => MyCustomType;

    get field(): StringModel {
        return this[_getPropertyModel]('field', StringModel, [false]) as StringModel;
    }
}

export default MyCustomTypeModel;
