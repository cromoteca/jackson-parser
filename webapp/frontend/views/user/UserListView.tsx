import User from "Frontend/generated/com/cromoteca/samples/endpoints/LoadersEndpoint/User";
import { Link, useLoaderData } from "react-router-dom";

export default function UserListView() {
    const users = useLoaderData() as User[];
    return (
        <section className="p-m">
            <h1>Users</h1>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>
                        <Link to={`../user/${user.id}`}>{user.name}</Link>
                    </li>
                ))}
                <li>
                    <Link to={`../user/999`}>Nobody</Link>
                </li>
            </ul>
        </section>
    );
}
