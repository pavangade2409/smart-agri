import { useState } from "react";
import type { FormEvent } from "react";
import { useMutation } from "@tanstack/react-query";
import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import { agentApi } from "../api/agentApi";
import "./farmerAgent.css";

export function FarmerAgentPage() {
  const [farmerId, setFarmerId] = useState(1);
  const [farmId, setFarmId] = useState(1);
  const [message, setMessage] = useState("");
  const [conversationId, setConversationId] = useState<string>();
  const mutation = useMutation({
    mutationFn: agentApi.ask,
    onSuccess: (response) => setConversationId(response.conversationId),
  });

  function submit(event: FormEvent) {
    event.preventDefault();
    const trimmedMessage = message.trim();
    if (!trimmedMessage) return;
    mutation.mutate({ farmerId, farmId, conversationId, message: trimmedMessage });
  }

  return (
    <section className="farmer-agent-page">
      <header className="farmer-agent-header">
        <div>
          <p className="farmer-agent-eyebrow">AI farm advisor</p>
          <h2>Farmer Agent</h2>
          <p>Ask for recommendations using your connected farm, crop, sensor, and weather data.</p>
        </div>
        <span className="agent-status">● Ready</span>
      </header>

      <form className="agent-context" onSubmit={submit}>
        <label>Farmer ID<input type="number" min="1" value={farmerId} onChange={(event) => setFarmerId(Number(event.target.value))} /></label>
        <label>Farm ID<input type="number" min="1" value={farmId} onChange={(event) => setFarmId(Number(event.target.value))} /></label>
      </form>

      <div className="agent-conversation" aria-live="polite">
        {!mutation.data && !mutation.isPending && <p className="agent-empty">Ask a question such as: “Should I irrigate my farm today?”</p>}
        {mutation.isPending && <LoadingSpinner />}
        {mutation.isError && <ErrorMessage message={getApiErrorMessage(mutation.error)} />}
        {mutation.data && <>
          <article className="agent-answer"><span>Agent response</span><p>{mutation.data.answer}</p></article>
          <article className="agent-recommendation"><span>Recommendation</span><p>{mutation.data.recommendation}</p><small>{mutation.data.source}</small></article>
        </>}
      </div>

      <form className="agent-composer" onSubmit={submit}>
        <label htmlFor="agent-message">Your question</label>
        <textarea id="agent-message" value={message} onChange={(event) => setMessage(event.target.value)} placeholder="Ask about irrigation, crop health, sensors, or weather..." rows={4} />
        <button type="submit" disabled={mutation.isPending || !message.trim()}>Ask Farmer Agent</button>
      </form>
    </section>
  );
}
