import MyEnum from './MyEnum.js';
import { EnumModel, _enum } from '@hilla/form';

class MyEnumModel extends EnumModel<typeof MyEnum> {
    readonly [_enum] = MyEnum;
}

export default MyEnumModel;
