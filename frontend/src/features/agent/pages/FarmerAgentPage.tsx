import { useState } from "react";
import type { FormEvent } from "react";
import { useMutation } from "@tanstack/react-query";
import { getApiErrorMessage } from "../../../api/apiError";
import { ErrorMessage } from "../../../components/common/ErrorMessage";
import { LoadingSpinner } from "../../../components/common/LoadingSpinner";
import { useFarms } from "../../farm/hooks/useFarms";
import { agentApi } from "../api/agentApi";
import "./farmerAgent.css";

export function FarmerAgentPage() {
  const farmsQuery = useFarms();
  const [farmId, setFarmId] = useState("");
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
    const parsedFarmId = Number(farmId);
    if (!Number.isInteger(parsedFarmId) || parsedFarmId <= 0) return;
    mutation.mutate({
      farmId: parsedFarmId,
      conversationId,
      message: trimmedMessage,
    });
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
        <label>
          Farm
          <select required value={farmId} onChange={(event) => setFarmId(event.target.value)}>
            <option value="">Select a farm</option>
            {farmsQuery.data?.map((farm) => (
              <option key={farm.id} value={farm.id}>{farm.name}</option>
            ))}
          </select>
        </label>
      </form>

      <div className="agent-conversation" aria-live="polite">
        {!mutation.data && !mutation.isPending && <p className="agent-empty">Ask a question such as: “Should I irrigate my farm today?”</p>}
        {farmsQuery.isLoading && <LoadingSpinner />}
        {farmsQuery.isError && <ErrorMessage message={getApiErrorMessage(farmsQuery.error)} />}
        {!farmsQuery.isLoading && !farmsQuery.isError && !farmsQuery.data?.length && (
          <p className="agent-empty">Add a farm before asking for farm-specific recommendations.</p>
        )}
        {mutation.isPending && <LoadingSpinner />}
        {mutation.isError && <ErrorMessage message={getApiErrorMessage(mutation.error)} />}
        {mutation.data && <>
          <article className="agent-answer"><span>Agent response</span><p>{mutation.data.answer}</p></article>
          <article className="agent-recommendation"><span>Source</span><small>{mutation.data.source}</small></article>
        </>}
      </div>

      <form className="agent-composer" onSubmit={submit}>
        <label htmlFor="agent-message">Your question</label>
        <textarea id="agent-message" value={message} onChange={(event) => setMessage(event.target.value)} placeholder="Ask about irrigation, crop health, sensors, or weather..." rows={4} />
        <button type="submit" disabled={mutation.isPending || !message.trim() || !farmId || farmsQuery.isLoading || !farmsQuery.data?.length}>Ask Farmer Agent</button>
      </form>
    </section>
  );
}
