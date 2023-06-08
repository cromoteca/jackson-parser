import Pair from "Frontend/generated/com/cromoteca/bfts/model/Pair";
import { ConfigurationEndpoint } from "Frontend/generated/endpoints";
import { useEffect, useState } from "react";

export default function HomeView() {
    const [storages, setStorages] = useState<Pair>();

    useEffect(() => {
        ConfigurationEndpoint.listStorages().then(setStorages);
    }, []);
    
    return <div>{storages?.first.}</div>;
}
