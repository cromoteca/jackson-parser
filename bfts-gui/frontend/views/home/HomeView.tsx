import Pair from "Frontend/generated/com/cromoteca/bfts/model/Pair";
import ConfigurationEndpoint from "Frontend/generated/com/example/application/endpoints/ConfigurationEndpoint";
import ConnectedStorageConfiguration from "Frontend/generated/com/example/application/endpoints/ConfigurationEndpoint/ConnectedStorageConfiguration";
import LocalStorageConfiguration from "Frontend/generated/com/example/application/endpoints/ConfigurationEndpoint/LocalStorageConfiguration";
import { useEffect, useState } from "react";

export default function HomeView() {
    const [storages, setStorages] = useState<Pair<LocalStorageConfiguration[], ConnectedStorageConfiguration[]>>();

    useEffect(() => {
        ConfigurationEndpoint.listStorages().then(setStorages);
    }, []);
    
    return <div>{storages?.first?.length}</div>;
}
