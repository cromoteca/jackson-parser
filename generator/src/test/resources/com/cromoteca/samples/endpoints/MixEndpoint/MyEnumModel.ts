import MyEnum from './MyEnum.js';
import { EnumModel, _enum } from '@vaadin/hilla-lit-form';

class MyEnumModel extends EnumModel<typeof MyEnum> {
    readonly [_enum] = MyEnum;
}

export default MyEnumModel;
