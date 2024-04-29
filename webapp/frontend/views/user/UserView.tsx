import User from "Frontend/generated/com/cromoteca/samples/endpoints/LoadersEndpoint/User";
import { useLoaderData } from "react-router-dom";

export default function UserView() {
    const user = useLoaderData() as User;
    return (
        <section className="p-m">
            Selected user: {user.name}
        </section>
    );
}
