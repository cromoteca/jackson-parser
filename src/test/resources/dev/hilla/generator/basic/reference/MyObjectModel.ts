import { _getPropertyModel as _getPropertyModel_1, AssertTrue as AssertTrue_1, BooleanModel as BooleanModel_1, NotEmpty as NotEmpty_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@hilla/form";
import type MyObject_1 from "./MyObject";
class MyObjectModel<T extends MyObject_1 = MyObject_1> extends ObjectModel_1<T> {
    static createEmptyValue: () => MyObject_1;
    get myBooleanField(): BooleanModel_1 {
        return this[_getPropertyModel_1]("myBooleanField", BooleanModel_1, [true, new AssertTrue_1()]) as BooleanModel_1;
    }
    get myStringField(): StringModel_1 {
        return this[_getPropertyModel_1]("myStringField", StringModel_1, [true, new NotEmpty_1()]) as StringModel_1;
    }
    get myIntField(): NumberModel_1 {
        return this[_getPropertyModel_1]("myIntField", NumberModel_1, [true]) as NumberModel_1;
    }
}
export default MyObjectModel;
