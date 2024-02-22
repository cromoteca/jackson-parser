import type MyOtherType from './MyOtherType.js';
import { ObjectModel, StringModel, _getPropertyModel } from '@vaadin/hilla-lit-form';

class MyOtherTypeModel<T extends MyOtherType = MyOtherType> extends ObjectModel<T> {
    declare static createEmptyValue: () => MyOtherType;

    get field(): StringModel {
        return this[_getPropertyModel]('field', StringModel, [false]) as StringModel;
    }
}

export default MyOtherTypeModel;
