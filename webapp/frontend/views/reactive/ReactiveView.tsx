import { Subscription } from "@vaadin/hilla-frontend";
import { Button, TextField } from "@vaadin/react-components";
import { useState } from "react";
import HelloReactEndpoint from "Frontend/generated/com/example/application/endpoints/helloreact/HelloReactEndpoint.js";

export default function ReactiveView() {
  const [serverTime, setServerTime] = useState("");
  const [subscription, setSubscription] = useState<Subscription<string> | undefined>();

  const toggleServerClock = async () => {
    if (subscription) {
      subscription.cancel();
      setSubscription(undefined);
    } else {
      setSubscription(HelloReactEndpoint.getClockCancellable().onNext((time) => {
        setServerTime(time);
      }));
    }
  }

  return (
      <section className="flex p-m gap-m items-end">
        <TextField label="Server time" value={serverTime} readonly />
        <Button onClick={toggleServerClock}>Toggle server clock</Button>
      </section>
  );
}
