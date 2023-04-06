import { _getPropertyModel as _getPropertyModel_1, NotBlank as NotBlank_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@hilla/form";
import type Answer_1 from "./Answer";
import ChoiceModel_1 from "./ChoiceModel";
class AnswerModel<T extends Answer_1 = Answer_1> extends ObjectModel_1<T> {
    static createEmptyValue: () => Answer_1;
    get choice(): ChoiceModel_1 {
        return this[_getPropertyModel_1]("choice", ChoiceModel_1, [true]) as ChoiceModel_1;
    }
    get question(): StringModel_1 {
        return this[_getPropertyModel_1]("question", StringModel_1, [true, new NotBlank_1()]) as StringModel_1;
    }
}
export default AnswerModel;
